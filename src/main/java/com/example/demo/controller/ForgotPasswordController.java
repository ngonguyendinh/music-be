package com.example.demo.controller;

import com.example.demo.util.ChangePassword;
import com.example.demo.util.MailBody;
import com.example.demo.entity.user.ForgotPassword;
import com.example.demo.entity.user.User;
import com.example.demo.form.InputOtp;
import com.example.demo.repository.ForgotPasswordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@AllArgsConstructor
public class ForgotPasswordController {
    private UserRepository userRepository;
    private EmailService emailService;
    private ForgotPasswordRepository forgotPasswordRepository;
    private PasswordEncoder passwordEncoder;
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyMail(@PathVariable("email") String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("kiểm tra lại email của bạn nhập"));
        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email).text("Mã OTP quên mật khẩu của bạn là :"+otp)
                .subject("OTP cho yêu cầu quên mật khẩu").build();
        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis()+70*1000))
                .user(user)
                .build();
        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);
        return ResponseEntity.ok("Email xác thực");
    }
    @PostMapping("/verify/otp/email/{email}")
    public ResponseEntity<String> verifyOtp(@RequestBody InputOtp otp, @PathVariable("email")String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("kiểm tra lại email của bạn nhập"));
        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp.getOtp(),user)
                .orElseThrow(()-> new RuntimeException("OTP không đúng vui lòng kiểm tra lại trong email của bạn: "+email));
        if (fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP đã hết hạn ", HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok("OTP xác thực thành công");
    }
    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword form, @PathVariable("email") String email){
        if(!Objects.equals(form.password(),form.repeatPassword())){
            return  new ResponseEntity<>("mật khẩu không khớp. Vui lòng kiểm tra lại",HttpStatus.EXPECTATION_FAILED);
        }
        String endcodePassword = passwordEncoder.encode(form.password());
        User user = userRepository.findByEmail(email).get();
        user.setPassword(endcodePassword);
        userRepository.save(user);
        return ResponseEntity.ok("thay đổi mật khẩu thành công");

    }
    private int otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }
}

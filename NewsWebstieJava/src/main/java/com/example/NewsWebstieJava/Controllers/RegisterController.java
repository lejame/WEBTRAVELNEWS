package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.util.random.RandomGeneratorFactory;

@Controller
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    AccountService accountService;
    @GetMapping("register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("register/account")
    public RedirectView registerSuccess(@RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("name") String name,
                                        HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        Account account = accountService.saveAccount(new Account(email, password, name, "user",
                RandomStringUtils.randomAlphanumeric(32), 0));

        if(account != null){
            register(account, getSiteURL(request));
            return new RedirectView("/login");
        }
        return  new RedirectView("/register");
    }
    @GetMapping("verify")
    public RedirectView gotoPage(@Param("code") String code, RedirectAttributes redirectAttributes){
        Account account = accountService.getCodeAccount(code);
        if(account == null){
            return new RedirectView("/login");
        }

        account.setEnable(1);
        accountService.updateEnable(account.getId());
        return new RedirectView("/home");
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    public void register(Account account, String siteURL)
            throws UnsupportedEncodingException, MessagingException {

        sendVerificationEmail(account, siteURL);
    }

    private void sendVerificationEmail(Account account, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = account.getEmail();
        String fromAddress = "phamtuan111203@gmail.com";
        String senderName = "WebSite Địa điểm và ẩm thực";
        String subject = "Vui lòng xác minh đăng ký của bạn";
        String content = "Chào [[name]],<br>"
                + "Vui lòng nhấp vào liên kết bên dưới để xác minh đăng ký của bạn:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Cảm ơn và chúc 1 ngày tốt lành,<br>"
                + "WebSite Địa điểm và ẩm thực.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", account.getFullname());
        String verifyURL = siteURL + "/verify?code=" + account.getCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }
}

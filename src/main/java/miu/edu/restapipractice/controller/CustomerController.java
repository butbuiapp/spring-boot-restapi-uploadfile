package miu.edu.restapipractice.controller;

import miu.edu.restapipractice.config.Constant;
import miu.edu.restapipractice.model.Customer;
import miu.edu.restapipractice.service.CustomerService;
import miu.edu.restapipractice.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showNewForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "new_customer";
    }

    @PostMapping("/save")
    public String saveUser(Model model,
                                 @ModelAttribute("customer") Customer customer,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        customer.setPhotos(fileName);

        Customer savedCus = customerService.save(customer);

        String uploadDir = Constant.CUSTOMER_PHOTO_PATH + "/" + savedCus.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        model.addAttribute("customer", savedCus);
        return "new_customer";
    }

}

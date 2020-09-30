package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sweater.domain.Message;
import sweater.domain.User;
import sweater.repository.MessageRepository;


import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) {
        Message message = new Message(text, tag, user);

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);

        return "main";
    }
}























//package sweater.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import sweater.domain.Message;
//import sweater.domain.User;
//import sweater.repository.MessageRepository;
//
//import java.util.Map;
//
//@Controller // модуль программы, который по пути "/greeting" слушает запросы пользователей и возвращает данные
//public class MainController {
//
//    private MessageRepository messageRepository;
//
//    @Autowired
//    public void setMessageRepository(MessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }
//
//    @GetMapping("/")
//    public String greeting(Map<String, Object> model) { //  model  - это то, куда мы будем складывать данные, которые нужно вернуть пользователю
//       // model.put("name", name);
//        return "greeting";  // возвращаем имя файла контейнеру спринг, которое нужно отобразить
//    }
//
//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<Message> messages;
//        if(filter !=null && !filter.isEmpty()){
//            messages = messageRepository.findByTag(filter);
//        } else {
//            messages = messageRepository.findAll();
//        }
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//        return "main";
//    }
//
//    @PostMapping("/main")
//    public String add(@AuthenticationPrincipal User user,
//            @RequestParam String text, @RequestParam String tag, Map<String, Object> model) {  //@RequestParam выдёргивает из form (при post или url при get запросе) значения
//        Message message = new Message(text, tag, user);
//        messageRepository.save(message);
//        Iterable<Message> messages = messageRepository.findAll();
//        model.put("messages", messages);
//        return "main";
//    }
//
////    @PostMapping("filter")
////    public String filter(@RequestParam String filter, Map<String, Object> model) {
////        Iterable<Message> messages;
////
//////        if(filter !=null && !filter.isEmpty()){
//////            messages = messageRepository.findByTag(filter);
//////        } else {
//////            messages = messageRepository.findAll();
//////        }
////        model.put("messages", messages);
////
////        return "main";
////
////
////    }
//}
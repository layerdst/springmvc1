package hello.springmvc.itemservice.web.basic;

import hello.springmvc.itemservice.domain.item.Item;
import hello.springmvc.itemservice.domain.item.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @Autowired // 생성자가 하나만 있으면 AutoWired 생략가능
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model) {
        log.info("url={}", "basic_items");
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 21200, 20));
    }
}

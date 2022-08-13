package hello.springmvc.itemservice.web.basic;

import hello.springmvc.itemservice.domain.item.Item;
import hello.springmvc.itemservice.domain.item.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 21200, 20));
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(@RequestParam String itemName,
                        @RequestParam int price,
                        @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }


//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동추가가 되기 때문에 생략이 가능하다.

        return "basic/item";
    }

    public String addItemV3(@ModelAttribute Item item) { // 클래스명의 첫글자를 소문자로 바꾼  Item -> item 이 model.addAttribute("item" , item) 으로 설정된다.
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동추가가 되기 때문에 생략이 가능하다.

        return "basic/item";
    }

    public String addItemV4(Item item) { //modelAttribute 자동적용됨됨        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동추가가 되기 때문에 생략이 가능하다.

        return "basic/item";
    }


}

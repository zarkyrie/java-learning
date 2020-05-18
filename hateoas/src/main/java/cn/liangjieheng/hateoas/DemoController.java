package cn.liangjieheng.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class DemoController {
    @RequestMapping("/demo1")
    public EntityModel<Payload> demo1() {
        Payload payload = new Payload();
        payload.setContent("hello");
        return new EntityModel<>(payload, linkTo(methodOn(DemoController.class).demo1()).withSelfRel(), linkTo(methodOn(DemoController.class).demo2()).withRel("world"));
    }

    @RequestMapping("/demo2")
    public EntityModel<Payload> demo2() {
        Payload payload = new Payload();
        payload.setContent("world");
        return new EntityModel<>(payload, linkTo(methodOn(DemoController.class).demo2()).withRel("world"));
    }
}

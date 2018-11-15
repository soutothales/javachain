package com.soutothales.javachain.resources;

import com.soutothales.javachain.domain.BlockChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/chain")
public class ChainResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Map<String, Object>> fullChain() {
        BlockChain blockchain = new BlockChain();

        return blockchain.getChain();
    }

}

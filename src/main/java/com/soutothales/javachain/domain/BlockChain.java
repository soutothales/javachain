package com.soutothales.javachain.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockChain {

    private List<Object> current_transactions;
    private List<Object> chain;

    public BlockChain(List<Object> current_transactions, List<Object> chain) {
        this.current_transactions = current_transactions;
        this.chain = chain;
    }

    public Map newBlock(Integer proof, String previous_hash) {
        Map<String, Object> block = new HashMap<String, Object>();

        block.put("index", chain.size() + 1);
        block.put("timestamp", java.time.LocalDateTime); // This can be changed to a ZonedDateTime
        block.put("transactions", current_transactions);
        block.put("proof", proof);
        block.put("previous_hash", previous_hash || this.hash256(chain.get(chain.size()-1));

        list.get(list.size() - 1);

        current_transactions = new ArrayList<>();
        chain.add(block);

        return block;

    }

    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }


    public Integer proofOfWork(Integer lastProof) {
//        Integer proof = 0;
//        while(this.valid_proof(last_proof, proof) == false) {
//            proof++;
//        }
//
//        return proof;

    }

    public static boolean validProof(Integer lastProof, Integer proof) {


    }


}

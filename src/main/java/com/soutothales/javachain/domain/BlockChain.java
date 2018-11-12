package com.soutothales.javachain.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockChain {

    private List<Object> current_transactions;
    private List<Map<String, Object>> chain;

    public BlockChain(List<Object> current_transactions, List<Map<String, Object>> chain) {
        this.current_transactions = current_transactions;
        this.chain = chain;
    }

    public Map newBlock(Integer proof, String previous_hash) {
        Map<String, Object> block = new HashMap<>();

        Instant timestamp = Instant.now();

        block.put("index", chain.size() + 1);
        block.put("timestamp", timestamp); // This can be changed to a ZonedDateTime
        block.put("transactions", this.current_transactions);
        block.put("proof", proof);
        try {
            block.put("previous_hash", this.hash256(this.chain.get(chain.size()-1)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.current_transactions = new ArrayList<>();
        this.chain.add(block);

        return block;

    }

    private String hash256(Map data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }
    private String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    public Integer newTransaction(String sender, String recipient, Integer amount) {

        this.current_transactions.add(new Transaction(sender, recipient, amount));

        return (Integer) this.chain.get(chain.size()-1).get("index");

    }

//    public Integer proofOfWork(Integer lastProof) {
//        Integer proof = 0;
//        while(this.valid_proof(last_proof, proof) == false) {
//            proof++;
//        }
//
//        return proof;
//
//    }

//    public static boolean validProof(Integer lastProof, Integer proof) {
//
//
//    }


}

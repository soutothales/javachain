package com.soutothales.javachain.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockChain {

    private List<Transaction> current_transactions = new ArrayList<>();

    public List<Transaction> getCurrent_transactions() {
        return current_transactions;
    }

    private List<Map<String, Object>> chain = new ArrayList<>();

    public BlockChain() {
        //this.newBlock(100, 1);
    }

    public List<Map<String, Object>> getChain() {
        return chain;
    }

    public Map newBlock(Integer proof, Integer previous_hash) {

        Map<String, Object> block = new HashMap<>();

        Instant timestamp = Instant.now();

        //System.out.println(getChain());

        block.put("index", chain.size() + 1);
        block.put("timestamp", timestamp);
        block.put("transactions", this.current_transactions);
        block.put("proof", proof);
        block.put("previous_hash", previous_hash);


        this.current_transactions = new ArrayList<>();
        this.chain.add(block);

        //System.out.println(block);

        return block;
    }

    public Map newBlock(Integer proof) {

        Map<String, Object> block = new HashMap<>();

        Instant timestamp = Instant.now();

        //System.out.println(getChain());

        block.put("index", chain.size() + 1);
        block.put("timestamp", timestamp);
        block.put("transactions", this.current_transactions);
        block.put("proof", proof);
        try {
                block.put("previous_hash", this.hash256(this.chain.get(chain.size()-1).toString()));
                // this.hash256(this.chain.get(chain.size()-1))
                // this line above hashes a block on the chain
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        this.current_transactions = new ArrayList<>();
        this.chain.add(block);

        //System.out.println(block);

        return block;
    }

    private String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());

        return bytesToHex(md.digest());
    }
    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));

        return result.toString();
    }

    public Integer newTransaction(String sender, String recipient, Integer amount) {

        this.current_transactions.add(new Transaction(sender, recipient, amount));

        return (Integer) this.chain.get(chain.size()-1).get("index");
    }

    public Integer proofOfWork(Integer last_proof) {
        Integer proof = 0;
        while(!this.validProof(last_proof, proof)) {
            proof++;
        }

        return proof;
    }

    public boolean validProof(Integer lastProof, Integer proof) {

        String strLastProof = lastProof.toString();
        String strProof = proof.toString();

        String guess = new StringBuilder(strLastProof).append(strProof).toString();
        String guess_hash = null;
        try {
            guess_hash = this.hash256(guess);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return guess_hash.substring(guess_hash.length() - 4).equals("0000");
    }

}

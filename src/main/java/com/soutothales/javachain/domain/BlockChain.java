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
        block.put("timestamp", timestamp);
        block.put("transactions", this.current_transactions);
        block.put("proof", proof);
        block.put("previous_hash", previous_hash);  // this.hash256(this.chain.get(chain.size()-1))
                                                    // this line above hashes a block on the chain
        this.current_transactions = new ArrayList<>();
        this.chain.add(block);

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

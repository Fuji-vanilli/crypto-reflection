package org.example.service;

import org.example.model.Crypto;

import java.util.List;

public interface ServiceCrypto {
    Crypto add(Crypto crypto);
    Crypto update(Crypto crypto);
    Crypto get(String id);
    List<Crypto> all();
    void delete(String id);
}

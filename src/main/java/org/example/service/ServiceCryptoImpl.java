package org.example.service;

import org.example.model.Crypto;
import org.example.repository.CryptoRepository;

import java.util.List;
import java.util.Optional;

public class ServiceCryptoImpl implements ServiceCrypto{
    private final CryptoRepository repository;

    public ServiceCryptoImpl(CryptoRepository cryptoRepository) {
        this.repository= cryptoRepository;
    }
    @Override
    public Crypto add(Crypto crypto) {

        return repository.save(crypto);
    }

    @Override
    public Crypto update(Crypto crypto) {
        return null;
    }

    @Override
    public Crypto get(String id) {
        final Optional<Crypto> crypto = repository.findById(id);
        return crypto.orElse(null);
    }

    @Override
    public List<Crypto> all() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}

package org.example;

import org.example.model.Crypto;
import org.example.repository.CryptoRepositoryOne;
import org.example.service.ServiceCrypto;
import org.example.service.ServiceCryptoImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner= new Scanner(new File("src/main/resources/config.txt"));
        final String clasRepositoryName= scanner.nextLine();

        Class cRepository= Class.forName(clasRepositoryName);
        CryptoRepositoryOne cryptoRepositoryOne= (CryptoRepositoryOne) cRepository.getDeclaredConstructor().newInstance();

       ServiceCrypto serviceCrypto= new ServiceCryptoImpl(cryptoRepositoryOne);
       //serviceCrypto.add(new Crypto("Gocoin", new BigDecimal(142)));
        serviceCrypto.delete("CPT-9");
        serviceCrypto.all().forEach(System.out::println);
    }
}
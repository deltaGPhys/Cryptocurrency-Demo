package com.zipcoder.cryptonator_api.services;

import com.zipcoder.cryptonator_api.domain.Datum;
import com.zipcoder.cryptonator_api.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    public Datum addDatum (Datum datum) {
        if (uniqueDatum(datum)) {
            return dataRepository.save(datum);
        } else {
            return null;
        }
    }

    public boolean uniqueDatum (Datum datum) {
        Datum retreivedDatum = dataRepository.findDatumByCryptoAndTimestamp(datum.getCrypto(),datum.getTimestamp());
        return (retreivedDatum == null) ? true : false;
    }

    public Iterable<Datum> getAllData () {
        return dataRepository.findAll();
    }

    public Iterable<Datum> getDataForCrypto (String crypto) {
        return dataRepository.findDataByCrypto(crypto);
    }

    public Iterable<Datum> getDataForCryptoAndSince (String crypto, Long timestamp) {
        return dataRepository.findDataByCryptoAndTimestampGreaterThanEqual(crypto, timestamp);
    }

    public Iterable<Datum> getDataSince (Long timestamp) {
        return dataRepository.findDataByTimestampGreaterThanEqual(timestamp);
    }


}

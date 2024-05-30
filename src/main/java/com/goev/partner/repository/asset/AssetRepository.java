package com.goev.partner.repository.asset;

import com.goev.partner.dao.asset.AssetDao;

import java.util.List;

public interface AssetRepository {
    AssetDao save(AssetDao log);
    AssetDao update(AssetDao log);
    void delete(Integer id);
    AssetDao findByUUID(String uuid);
    AssetDao findById(Integer id);
    List<AssetDao> findAllByIds(List<Integer> ids);
    List<AssetDao> findAll();
}
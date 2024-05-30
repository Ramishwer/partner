package com.goev.partner.repository.asset;

import com.goev.partner.dao.asset.AssetTypeDao;

import java.util.List;

public interface AssetTypeRepository {
    AssetTypeDao save(AssetTypeDao log);
    AssetTypeDao update(AssetTypeDao log);
    void delete(Integer id);
    AssetTypeDao findByUUID(String uuid);
    AssetTypeDao findById(Integer id);
    List<AssetTypeDao> findAllByIds(List<Integer> ids);
    List<AssetTypeDao> findAll();
}
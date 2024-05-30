package com.goev.partner.repository.asset.impl;

import com.goev.partner.dao.asset.AssetTypeDao;
import com.goev.partner.repository.asset.AssetTypeRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.AssetTypesRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.AssetTypes.ASSET_TYPES;

@Slf4j
@Repository
@AllArgsConstructor
public class AssetTypeRepositoryImpl implements AssetTypeRepository {

    private final DSLContext context;

    @Override
    public AssetTypeDao save(AssetTypeDao client) {
        AssetTypesRecord assetTypesRecord = context.newRecord(ASSET_TYPES, client);
        assetTypesRecord.store();
        client.setId(assetTypesRecord.getId());
        client.setUuid(assetTypesRecord.getUuid());
        return client;
    }

    @Override
    public AssetTypeDao update(AssetTypeDao client) {
        AssetTypesRecord assetTypesRecord = context.newRecord(ASSET_TYPES, client);
        assetTypesRecord.update();
        return client;
    }

    @Override
    public void delete(Integer id) {
        context.update(ASSET_TYPES).set(ASSET_TYPES.STATE, RecordState.DELETED.name()).where(ASSET_TYPES.ID.eq(id)).execute();
    }

    @Override
    public AssetTypeDao findByUUID(String uuid) {
        return context.selectFrom(ASSET_TYPES).where(ASSET_TYPES.UUID.eq(uuid)).fetchAnyInto(AssetTypeDao.class);
    }

    @Override
    public AssetTypeDao findById(Integer id) {
        return context.selectFrom(ASSET_TYPES).where(ASSET_TYPES.ID.eq(id)).fetchAnyInto(AssetTypeDao.class);
    }

    @Override
    public List<AssetTypeDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(ASSET_TYPES).where(ASSET_TYPES.ID.in(ids)).fetchInto(AssetTypeDao.class);
    }

    @Override
    public List<AssetTypeDao> findAll() {
        return context.selectFrom(ASSET_TYPES).fetchInto(AssetTypeDao.class);
    }
}

package com.goev.partner.dao.asset;

import com.goev.lib.dao.BaseDao;
import com.goev.partner.dto.asset.AssetDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AssetDao extends BaseDao {
    private String assetName;
    private String assetDescription;
    private String assetImageUrl;
    private String parentType;
    private String parentName;
    private String serialNo;
    private Integer assetTypeId;

    public static AssetDao fromDto(AssetDto assetDto, Integer assetTypeId) {
        AssetDao assetDao = new AssetDao();
        assetDao.setAssetName(assetDto.getAssetName());
        assetDao.setAssetDescription(assetDto.getAssetDescription());
        assetDao.setAssetImageUrl(assetDto.getAssetImageUrl());
        assetDao.setParentType(assetDto.getParentType());
        assetDao.setSerialNo(assetDto.getSerialNo());
        assetDao.setAssetTypeId(assetTypeId);
        return assetDao;
    }
}

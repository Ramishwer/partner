package com.goev.partner.dao.asset;

import com.goev.lib.dao.BaseDao;
import com.goev.partner.dto.asset.AssetTypeDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AssetTypeDao extends BaseDao {
    private String name;
    private String description;
    private String parentType;

    public static AssetTypeDao fromDto(AssetTypeDto assetTypeDto) {
        AssetTypeDao assetTypeDao = new AssetTypeDao();
        assetTypeDao.setName(assetTypeDto.getName());
        assetTypeDao.setDescription(assetTypeDto.getDescription());
        assetTypeDao.setParentType(assetTypeDto.getParentType());
        return assetTypeDao;
    }
}

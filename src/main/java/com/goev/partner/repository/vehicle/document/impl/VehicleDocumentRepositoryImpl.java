package com.goev.partner.repository.vehicle.document.impl;

import com.goev.partner.dao.vehicle.document.VehicleDocumentDao;
import com.goev.partner.repository.vehicle.document.VehicleDocumentRepository;
import com.goev.lib.enums.RecordState;
import com.goev.record.partner.tables.records.VehicleDocumentsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.goev.record.partner.tables.VehicleDocuments.VEHICLE_DOCUMENTS;

@Repository
@AllArgsConstructor
@Slf4j
public class VehicleDocumentRepositoryImpl implements VehicleDocumentRepository {
    private final DSLContext context;

    @Override
    public VehicleDocumentDao save(VehicleDocumentDao vehicleDocument) {
        VehicleDocumentsRecord vehiclesDocumentRecord = context.newRecord(VEHICLE_DOCUMENTS, vehicleDocument);
        vehiclesDocumentRecord.store();
        vehicleDocument.setId(vehiclesDocumentRecord.getId());
        vehicleDocument.setUuid(vehicleDocument.getUuid());
        return vehicleDocument;
    }

    @Override
    public VehicleDocumentDao update(VehicleDocumentDao vehicleDocument) {
        VehicleDocumentsRecord vehiclesDocumentRecord = context.newRecord(VEHICLE_DOCUMENTS, vehicleDocument);
        vehiclesDocumentRecord.update();
        return vehicleDocument;
    }

    @Override
    public void delete(Integer id) {
        context.update(VEHICLE_DOCUMENTS).set(VEHICLE_DOCUMENTS.STATE, RecordState.DELETED.name()).where(VEHICLE_DOCUMENTS.ID.eq(id)).execute();
    }

    @Override
    public VehicleDocumentDao findByUUID(String uuid) {
        return context.selectFrom(VEHICLE_DOCUMENTS).where(VEHICLE_DOCUMENTS.UUID.eq(uuid)).fetchAnyInto(VehicleDocumentDao.class);
    }

    @Override
    public VehicleDocumentDao findById(Integer id) {
        return context.selectFrom(VEHICLE_DOCUMENTS).where(VEHICLE_DOCUMENTS.ID.eq(id)).fetchAnyInto(VehicleDocumentDao.class);
    }

    @Override
    public List<VehicleDocumentDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(VEHICLE_DOCUMENTS).where(VEHICLE_DOCUMENTS.ID.in(ids)).fetchInto(VehicleDocumentDao.class);
    }

    @Override
    public List<VehicleDocumentDao> findAllByVehicleId(Integer vehicleId) {
        return context.selectFrom(VEHICLE_DOCUMENTS).where(VEHICLE_DOCUMENTS.VEHICLE_ID.eq(vehicleId))
                .and(VEHICLE_DOCUMENTS.STATE.eq(RecordState.ACTIVE.name()))
                .fetchInto(VehicleDocumentDao.class);
    }

    @Override
    public List<VehicleDocumentDao> findAllByVehicleIdAndDocumentTypeIds(Integer vehicleId, List<Integer> ids) {
        return context.selectFrom(VEHICLE_DOCUMENTS).where(VEHICLE_DOCUMENTS.VEHICLE_ID.eq(vehicleId))
                .and(VEHICLE_DOCUMENTS.STATE.eq(RecordState.ACTIVE.name()))
                .and(VEHICLE_DOCUMENTS.VEHICLE_DOCUMENT_TYPE_ID.in(ids))
                .fetchInto(VehicleDocumentDao.class);
    }

    @Override
    public Map<Integer, VehicleDocumentDao> getExistingDocumentMap(Integer vehicleId, List<Integer> activeDocumentTypeIds) {
        List<VehicleDocumentDao> existingDocuments = findAllByVehicleIdAndDocumentTypeIds(vehicleId, activeDocumentTypeIds);
        Map<Integer, VehicleDocumentDao> documentMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(existingDocuments)) {
            for (VehicleDocumentDao document : existingDocuments) {
                if (activeDocumentTypeIds.contains(document.getId())) {
                    documentMap.put(document.getVehicleDocumentTypeId(), document);
                }
            }
        }
        return documentMap;
    }

}
package az.grow.lab.jdbctemplatetask4.repository;

import az.grow.lab.jdbctemplatetask4.domain.PersonalInformation;
import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import az.grow.lab.jdbctemplatetask4.enums.ConfirmStatus;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PersonalRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void savePersonal(PersonalInformation personalInformation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO personal_information" +
                " (passport_id,home_number,work_number,mobile_number,email,country,city,street,postal_code,action_status)" +
                "VALUES (:passport_ID,:homeNumber,:workNumber,:mobileNumber,:email,:country,:city,:street,:postalCode,:actionStatus)";
        namedParameterJdbcTemplate.update(query, new MapSqlParameterSource()
                        .addValue("passport_ID", personalInformation.getPassportId())
                        .addValue("homeNumber", personalInformation.getHomeNumber())
                        .addValue("workNumber", personalInformation.getWorkNumber())
                        .addValue("mobileNumber", personalInformation.getMobileNumber())
                        .addValue("email", personalInformation.getEmail())
                        .addValue("country", personalInformation.getCountry())
                        .addValue("city", personalInformation.getCity())
                        .addValue("street", personalInformation.getStreet())
                        .addValue("postalCode", personalInformation.getPostalCode())
                        .addValue("actionStatus", ActionStatus.WAITING_FOR_INITIAL_APPROVE.name())
                , keyHolder);
    }

    public void updatePersonalActionStatus(Integer id, ConfirmStatus confirmStatus, String rejectReason) {
        String query = null;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", id);

        if (confirmStatus == ConfirmStatus.APPROVE) {
            query = "UPDATE personal_information SET action_status = :approve WHERE id = :ID";
            mapSqlParameterSource.addValue("approve", ActionStatus.INITIAL_CHECK_APPROVED.name());
        } else if (confirmStatus == ConfirmStatus.NEED_TO_IMPROVE) {
            query = "UPDATE personal_information SET action_status = :needToImprove, reject_reason = :rejectReason WHERE id = :ID";
            mapSqlParameterSource.addValue("needToImprove", ConfirmStatus.NEED_TO_IMPROVE.name());
        } else if (confirmStatus == ConfirmStatus.DECLINE) {
            query = "UPDATE personal_information SET action_status = :decline, reject_reason = :rejectReason WHERE id = :ID";
            mapSqlParameterSource.addValue("decline", ConfirmStatus.DECLINE.name());
        }
        mapSqlParameterSource.addValue("rejectReason", rejectReason);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);

    }

}

package az.grow.lab.jdbctemplatetask4.repository;

import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import az.grow.lab.jdbctemplatetask4.domain.PassportInformation;
import az.grow.lab.jdbctemplatetask4.enums.ConfirmStatus;
import az.grow.lab.jdbctemplatetask4.enums.FinalStatus;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PassportRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void savePassport(PassportInformation passportInformation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO passport_information (name,surname,patronymic,birth_date,gender,passport_number,action_status,final_status)" +
                " VALUES (:name,:surname,:patronymic,:birthDate,:gender,:passportNumber,:actionStatus,:finalStatus)";
        namedParameterJdbcTemplate.update(query, new MapSqlParameterSource()
                        .addValue("name", passportInformation.getName())
                        .addValue("surname", passportInformation.getSurname())
                        .addValue("patronymic", passportInformation.getPatronymic())
                        .addValue("birthDate", passportInformation.getBirthDate())
                        .addValue("gender", passportInformation.getGender().name())
                        .addValue("passportNumber", passportInformation.getPassportNumber())
                        .addValue("actionStatus", ActionStatus.WAITING_FOR_IDENTITY_APPROVE.name())
                        .addValue("finalStatus", FinalStatus.IN_PROGRESS.name())
                , keyHolder);
    }

    public void updatePassportActionStatus(Integer id, ConfirmStatus confirmStatus, String rejectReason) {
        String query = null;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", id);

        if (confirmStatus == ConfirmStatus.APPROVE) {
            query = "UPDATE passport_information SET action_status = :approve WHERE id = :ID";
            mapSqlParameterSource.addValue("approve", ActionStatus.IDENTITY_CHECK_APPROVED.name());
        } else if (confirmStatus == ConfirmStatus.NEED_TO_IMPROVE) {
            query = "UPDATE passport_information SET action_status = :needToImprove, reject_reason = :rejectReason WHERE id = :ID";
            mapSqlParameterSource.addValue("needToImprove", ConfirmStatus.NEED_TO_IMPROVE.name());
        } else if (confirmStatus == ConfirmStatus.DECLINE) {
            query = "UPDATE passport_information SET action_status = :decline, reject_reason = :rejectReason WHERE id = :ID";
            mapSqlParameterSource.addValue("decline", ConfirmStatus.DECLINE.name());
        }
        mapSqlParameterSource.addValue("rejectReason", rejectReason);

        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }
}

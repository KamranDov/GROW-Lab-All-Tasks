package az.grow.lab.jdbctemplatetask4.repository;

import az.grow.lab.jdbctemplatetask4.domain.LoanInformation;
import az.grow.lab.jdbctemplatetask4.dto.LoanDto;
import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import az.grow.lab.jdbctemplatetask4.enums.ConfirmStatus;
import az.grow.lab.jdbctemplatetask4.enums.FinalStatus;
import az.grow.lab.jdbctemplatetask4.exception.LoanGetByIdException;
import az.grow.lab.jdbctemplatetask4.exception.LoanNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LoanRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveLoan(LoanInformation loanInformation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO loan_information" +
                " (passport_id, product_id, total_amount, pre_amount, interest_rate, action_status, final_status)" +
                "VALUES (:passportID,:productID,:totalAmount,:preAmount,:interestRate,:actionStatus,:finalStatus)";
        namedParameterJdbcTemplate.update(query, new MapSqlParameterSource()
                        .addValue("passportID", loanInformation.getPassportId())
                        .addValue("productID", loanInformation.getProductId())
                        .addValue("totalAmount", loanInformation.getTotalAmount())
                        .addValue("preAmount", loanInformation.getPreAmount())
                        .addValue("interestRate", loanInformation.getInterestRate())
                , keyHolder);
    }

    public LoanDto getLoanDtoById(Integer id) {
        String querySql = "SELECT * FROM loan_information WHERE id = :ID";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", id);
        List<LoanDto> loanDtoList = namedParameterJdbcTemplate.query(querySql, mapSqlParameterSource, (resultSet, rowNumber) -> {
            LoanDto loanDto = new LoanDto();
            loanDto.setId(resultSet.getInt("id"));
            loanDto.setProductId(resultSet.getInt("product_id"));
            loanDto.setTotalAmount(resultSet.getBigDecimal("total_amount"));
            loanDto.setPreAmount(resultSet.getBigDecimal("pre_amount"));
            loanDto.setInterestRate(resultSet.getBigDecimal("interest_rate"));
            loanDto.setActionStatus(ActionStatus.valueOf(resultSet.getString("action_status")));
            return loanDto;
        });
        if (loanDtoList.isEmpty()) {
            throw new LoanGetByIdException("Loan not found with ID: " + id);
        }
        return loanDtoList.get(0);
    }

    public void updateLoanActionStatus(Integer id, ConfirmStatus confirmStatus, String rejectReason) {
        String query = null;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", id);

        if (confirmStatus == ConfirmStatus.APPROVE) {
            query = "UPDATE loan_information SET action_status = :approve WHERE id = :ID";
            mapSqlParameterSource.addValue("approve", ActionStatus.FINAL_CHECK_APPROVED.name());
        } else if (confirmStatus == ConfirmStatus.NEED_TO_IMPROVE) {
            query = "UPDATE loan_information SET action status = :needToImprove, reject_reason = :rejectReason WHERE id = :ID";
            mapSqlParameterSource.addValue("needToImprove", ConfirmStatus.NEED_TO_IMPROVE.name());
        } else if (confirmStatus == ConfirmStatus.DECLINE) {
            query = "UPDATE loan_information SET action_status = :decline, reject_reason = :rejectReason WHERE id = :ID";
            mapSqlParameterSource.addValue("decline", ConfirmStatus.DECLINE.name());
        }
        mapSqlParameterSource.addValue("rejectReason", rejectReason);
        namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
    }
}

package pay.token.api.domain.card.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Service
public class SellerEntryValidateService {


    @Transactional(readOnly = true)
    public void validate() {


    }


}

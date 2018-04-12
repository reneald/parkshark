package be.claimed.domain.members;

import be.claimed.domain.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MemberRepository extends AbstractRepository <Member>{

}

package rpsdal.repositories;

import model.RoundResult;
import rpsshared.interfaces.IDataContext;
import rpsshared.interfaces.IRepository;

public class RoundResultRepository extends RepositoryBase<RoundResult> implements IRepository<RoundResult> {

    public RoundResultRepository(IDataContext context)
    {
        super(context);
    }

}

package repository;

public interface EntityRepository<T>{
    public void updateEntityInRepo(T entitySource, T entityDestin);
    public void updateEntityInRepo(T entity);

}

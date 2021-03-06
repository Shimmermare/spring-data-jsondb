package org.mambofish.spring.data.jsondb.repository;

import io.jsondb.JsonDBTemplate;
import org.mambofish.spring.data.jsondb.query.JsonDBQueryLookupStrategy;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;

import java.util.Optional;

/**
 * @author vince
 */
public class JsonDBRepositoryFactory extends RepositoryFactorySupport {

    private final JsonDBTemplate template;

    public JsonDBRepositoryFactory(JsonDBTemplate template) {
        this.template = template;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        super.setBeanClassLoader(classLoader);
    }

    @Override
    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> type) {
        return new JsonDBEntityInformation(type);
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation information) {
        return getTargetRepositoryViaReflection(information, information.getDomainType(), template);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata repositoryMetadata) {
        return JsonDBRepositoryImpl.class;
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(QueryLookupStrategy.Key key, QueryMethodEvaluationContextProvider evaluationContextProvider) {
        return Optional.of(new JsonDBQueryLookupStrategy(template));
    }
}

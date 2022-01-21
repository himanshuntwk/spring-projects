package com.himanshu.graphqlserver.datafetchers;

import com.himanshu.graphqlserver.datastore.PersonStore;
import com.himanshu.graphqlserver.domain.Person;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class PersonDatafetcher {

    /**
     * Data fetcher to fetch all person objects in the list.
     * @return List
     */
    @DgsQuery
    public List<Person> allPersons() {
        return PersonStore.getPersonList();
    }

    /**
     * Data fetcher to search and return person with specific Id.
     * @param searchPersonId
     * @return Person
     */
    @DgsQuery
    public Person searchPerson(@InputArgument Integer searchPersonId) {
        return PersonStore.getPersonList()
                .stream()
                .filter(record -> record.getPersonId()==searchPersonId)
                .findFirst().get();
    }
}

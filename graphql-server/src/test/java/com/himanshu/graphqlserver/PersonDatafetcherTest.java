package com.himanshu.graphqlserver;

import com.himanshu.graphqlserver.datafetchers.PersonDatafetcher;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import graphql.ExecutionResult;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DgsAutoConfiguration.class, PersonDatafetcher.class})
class PersonDatafetcherTest {

	@Autowired
	DgsQueryExecutor dgsQueryExecutor;

	@Test
	void searchPerson() {
		ExecutionResult person = dgsQueryExecutor.execute(
				"{ searchPerson(searchPersonId: 1) { personId personName}}");
		JSONObject jsonObject;
		try {
			jsonObject = (new JSONObject(person.getData().toString())).getJSONObject("searchPerson");
			assertEquals(jsonObject.get("personId"), 1);
			assertEquals(jsonObject.get("personName"), "personName1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

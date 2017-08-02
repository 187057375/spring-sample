/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.birdstudio;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import cn.birdstudio.transaction.domain.Transaction;
import cn.birdstudio.transaction.service.TransactionService;
import cn.birdstudio.user.domain.User;
import cn.birdstudio.user.service.UserService;

/**
 * @author Sam Zhang
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Resource
	private UserService userService;

	@Resource
	private TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void doSomething() {
		User user = userService.getUser("Jenni");
		logger.info(user.toString());
		Transaction transaction = transactionService.getTransaction(1L);
		if (transaction != null)
			logger.info(transaction.toString());
		else
			logger.info("transaction is null");

	}

}

package com.apixandru.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EnableJpaRepositories
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private CustomNodeRepo customRepo;

    @Autowired
    private NodeRepo jpaRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private void createNodes() {

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Node node = new Node();
            node.setName("node " + i);
            nodes.add(node);
        }
        jpaRepo.save(nodes);
        jpaRepo.flush();

        entityManager.clear();
    }

    private void linkNodes() {
        Random random = new Random();
        List<Node> all = jpaRepo.findAll();
        for (Node node : all) {
            int i = random.nextInt(all.size());
            Node node1 = all.get(i);
            if (!node1.canReach(node) && !node.canReach(node1)) {
                node1.getChildren().add(node);
            }
        }

        jpaRepo.save(all);
        jpaRepo.flush();

        entityManager.clear();
    }

    @Override
    public void run(String... strings) throws Exception {
        createNodes();
        System.out.println("///");
        linkNodes();
        System.out.println("///");
        fetchJpaRepository();
        System.out.println("///");
        fetchCustomRepository();
        System.out.println("///");
        ensureSameElements();
    }

    private void ensureSameElements() {
        if (!customRepo.findAll().equals(jpaRepo.findAll())) {
            throw new IllegalArgumentException();
        }
    }

    private void fetchCustomRepository() {
        iterateAndCheck(customRepo.findAll());
    }

    private void fetchJpaRepository() {
        iterateAndCheck(jpaRepo.findAll());
    }

    private void iterateAndCheck(List<Node> all) {
        System.out.println(all.size());
        all.forEach(System.out::println);
        entityManager.clear();
    }

}

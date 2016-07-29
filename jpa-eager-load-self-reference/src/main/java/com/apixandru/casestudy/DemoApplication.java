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
    private Salmonella salmonella;

    @Autowired
    private NodeRepo nodeRepo;

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
        nodeRepo.save(nodes);
        nodeRepo.flush();

        entityManager.clear();
    }

    private void generateLinks() {

        Random random = new Random();
        List<Node> all = nodeRepo.findAll();
        for (Node node : all) {
            int i = random.nextInt(all.size());
            Node node1 = all.get(i);
            if (!node1.canReach(node) && !node.canReach(node1)) {
                node1.getChildren().add(node);
                try {
                    nodeRepo.save(node1);
                } catch (Exception e) {
                    System.out.println("a");
                }
            }
        }
        nodeRepo.flush();
        entityManager.clear();
    }

    @Override
    public void run(String... strings) throws Exception {
        createNodes();
        System.out.println("///");
        generateLinks();
        System.out.println("///");
        printLinks();
        System.out.println("///");
        printSalmonella();
    }

    private void printSalmonella() {
        for (Node node : salmonella.findAll()) {
            System.out.println(node);
        }
        entityManager.clear();
    }

    private void printLinks() {
        for (Node node : nodeRepo.findAll()) {
            System.out.println(node);
        }
        entityManager.clear();
    }

}

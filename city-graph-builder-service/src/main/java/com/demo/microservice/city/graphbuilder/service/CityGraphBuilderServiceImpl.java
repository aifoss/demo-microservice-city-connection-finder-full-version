package com.demo.microservice.city.graphbuilder.service;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;

/**
 * Implementation of {@link CityGraphBuilderService}.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@Service
public class CityGraphBuilderServiceImpl implements CityGraphBuilderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityGraphBuilderServiceImpl.class);
	
	private static final String DELIMITER = ", ";
	
	@Value("${input.file}")
	private String inputFilePath;

	@Override
	public CityGraph buildGraph() throws IOException {
		LOGGER.info("Starting to build undirected graph ...");
		
		CityGraph graph = new CityGraph();
		build(graph);

        LOGGER.info("Undirected Graph: {}", graph);
        LOGGER.info("Building undirected graph complete");
        
        return graph;
    }
	
	@Override
	public DirectedCityGraph buildDirectedGraph() throws IOException {
		LOGGER.info("Starting to build directed graph ...");
		
		DirectedCityGraph graph = new DirectedCityGraph();
		build(graph);
		
		LOGGER.info("Directed Graph: {}", graph);
        LOGGER.info("Building directed graph complete");
        
        return graph;
	}
	
	private void build(CityGraph graph) throws IOException {
		Resource resource = new ClassPathResource(inputFilePath);
		Scanner scanner = new Scanner(resource.getInputStream());
		
		while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            String[] cityNames = line.split(DELIMITER);
            if (cityNames.length != 2) {
                continue;
            }

            graph.addEdge(cityNames[0].trim().toLowerCase(), cityNames[1].trim().toLowerCase());
        }
		
		scanner.close();
	}
	
}

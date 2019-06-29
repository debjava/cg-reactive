package com.ddlab.rnd.controller;

import com.ddlab.rnd.dto.CGModel;
import com.ddlab.rnd.dto.ReviewModel;
import com.ddlab.rnd.util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/reactive")
public class CGController {

  protected static Logger logger = LoggerFactory.getLogger(CGController.class);

  @PostMapping(path = "/createCG", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<CGModel> createCG(@RequestBody List<ReviewModel> reviewModelList) {
    logger.debug("Review Model in Json \n{}", CommonUtil.convertToJsonString(reviewModelList));
    return Flux.fromIterable(reviewModelList)
        .map(
            reviewModel -> {
              System.out.println("Input Data VM ::: " + reviewModel);
              return getCGModel(reviewModel);
            })
        .delayElements(Duration.ofSeconds(3));
  }

  @GetMapping(path = "/getAllCG/{noOfCG}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<CGModel> getTempFromJsonString(@PathVariable(value = "noOfCG") int noOfCG) {
    ObjectMapper mapper = new ObjectMapper();
    logger.debug("No of CGs to be displayed: {}", noOfCG);
    List<ReviewModel> modelList = new ArrayList<>();
    for (int i = 0; i < noOfCG; i++) {
      ReviewModel model = new ReviewModel();
      model.setJournalSize(i + 10);
      model.setSrcDcName("DataCenter-" + i);
      model.setSrcVmName("VM-" + i);
      modelList.add(model);
    }
    return Flux.fromIterable(modelList)
        .map(
            reviewModel -> {
              System.out.println("Review Model ::: " + reviewModel);
              return getCGModel(reviewModel);
            })
        .delayElements(Duration.ofSeconds(3));
  }

  private CGModel getCGModel(ReviewModel reviewModel) {
    logger.debug("Review Model: {}", reviewModel);
    logger.debug("Time Now: {}", LocalDate.now());
    CGModel cgModel = new CGModel();
    cgModel.setCgName(reviewModel.getSrcVmName());
    cgModel.setMessage("Data Center Name : " + reviewModel.getSrcDcName());
    cgModel.setTimestamp(LocalDateTime.now().toString());
    return cgModel;
  }
}

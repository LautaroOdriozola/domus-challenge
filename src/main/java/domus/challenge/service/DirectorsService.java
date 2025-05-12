package domus.challenge.service;

import java.util.List;
import java.util.Map;

public interface DirectorsService {

    Map<String, List<String>> getDirectorsAboveThreshold(Integer threshold);
}

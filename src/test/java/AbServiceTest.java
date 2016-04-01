import com.opensource.leo.model.AbRequest;
import com.opensource.leo.model.AbResponse;
import com.opensource.leo.model.Result;
import com.opensource.leo.service.AbService;
import com.opensource.leo.service.AbServiceImpl;
import com.opensource.leo.service.ExperMetaService;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbServiceTest {
    List<String> cookies;
    AbService abService;

    @Before
    public void prepare() throws NoSuchAlgorithmException {
        ExperMetaService exprMetaService = new ExprMetaServiceImpl();
        abService = new AbServiceImpl(exprMetaService);
        cookies = new ArrayList<String>();
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        for (int i = 0; i < 10000; i++) {
            cookies.add(String.valueOf(sr.nextInt()));
        }
    }

    @Test
    public void abService() {
        Map<String, Long> map = new HashMap<String, Long>();
        for (int i = 0; i < 100; i++) {
            for (String cookie : cookies) {
                AbRequest request = new AbRequest();
                request.setCookie(cookie);
                Result<AbResponse> result = abService.ab(request);
                List<AbResponse.AbEntity> abEntities = result.getModule().getEntrys();
                for (AbResponse.AbEntity entity : abEntities) {
                    String key = "expr:" + entity.getExprName() + " bucket:" + entity.getBucketId();
                    if (map.containsKey(key)) {
                        long count = map.get(key);
                        count++;
                        map.put(key, count);
                    } else
                        map.put(key, 0L);
                }
            }
        }
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}

import com.google.common.collect.Lists;
import com.opensource.leo.meta.Bucket;
import com.opensource.leo.meta.Exper;
import com.opensource.leo.service.ExperMetaService;

import java.util.ArrayList;
import java.util.List;

public class ExprMetaServiceImpl implements ExperMetaService {

    @Override
    public List<Exper> getExperiments() {
        List<Exper> expers = new ArrayList<Exper>();
        for (int i = 1; i < 6; i++) {
            Exper exper = new Exper();
            exper.setId(i);
            exper.setHashAlg("MD5");
            exper.setName("name" + i);
            Bucket bucket1 = new Bucket();
            bucket1.setId(i * 10 + 1);
            bucket1.setStart(0);
            bucket1.setEnd(25);
            Bucket bucket2 = new Bucket();
            bucket2.setId(i * 10 + 2);
            bucket2.setStart(25);
            bucket2.setEnd(50);
            Bucket bucket3 = new Bucket();
            bucket3.setId(i * 10 + 3);
            bucket3.setStart(50);
            bucket3.setEnd(75);
            Bucket bucket4 = new Bucket();
            bucket4.setId(i * 10 + 4);
            bucket4.setStart(75);
            bucket4.setEnd(100);
            exper.setBuckets(Lists.newArrayList(bucket1, bucket2, bucket3, bucket4));
            expers.add(exper);
        }
        return expers;
    }
}

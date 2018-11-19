package recommender;

import org.grouplens.lenskit.ItemRecommender;
import org.grouplens.lenskit.ItemScorer;
import org.grouplens.lenskit.Recommender;
import org.grouplens.lenskit.RecommenderBuildException;
import org.grouplens.lenskit.core.LenskitConfiguration;
import org.grouplens.lenskit.core.LenskitRecommender;
import org.grouplens.lenskit.data.dao.EventDAO;
import org.grouplens.lenskit.data.dao.ItemDAO;
import org.grouplens.lenskit.data.dao.UserDAO;
import org.grouplens.lenskit.scored.ScoredId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recommender_dao.*;

import java.io.File;
import java.util.List;

public class content_based_rec_main {
    private static final Logger logger = LoggerFactory.getLogger(content_based_rec_main.class);

    public static void main(String[] args) throws RecommenderBuildException {
        LenskitConfiguration config = configureRecommender();

        logger.info("building recommender");
        Recommender rec = LenskitRecommender.build(config);

        if (args.length == 0) {
            logger.error("No users specified; provide user IDs as command line arguments");
        }

        ItemRecommender irec = rec.getItemRecommender();

        /* Belum diubah inputan masih pake argument, nanti diubah lagi */
        assert irec != null;
        try {
            // Generate 5 recommendations for each user
            for (String user: args) {
                long uid;
                try {
                    uid = Long.parseLong(user);
                } catch (NumberFormatException e) {
                    logger.error("cannot parse user {}", user);
                    continue;
                }
                logger.info("searching for recommendations for user {}", user);
                List<ScoredId> recs = irec.recommend(uid, 5);
                if (recs.isEmpty()) {
                    logger.warn("no recommendations for user {}, do they exist?", uid);
                }
                System.out.format("recommendations for user %d:\n", uid);
                for (ScoredId id: recs) {
                    System.out.format("  %d: %.4f\n", id.getId(), id.getScore());
                }
            }
        } catch (UnsupportedOperationException e) {
            if (e.getMessage().equals("stub implementation")) {
                System.out.println("Congratulations, the stub builds and runs!");
            }
        }
    }


    // Belum ubah inputan database, masih harus pake file .csv

    // LensKit configuration API generates some unchecked warnings, turn them off
    @SuppressWarnings("unchecked")
    private static LenskitConfiguration configureRecommender() {

        LenskitConfiguration config = new LenskitConfiguration();
        // configure the rating data source
        config.bind(EventDAO.class)
                .to(MOOCRatingDAO.class);
        config.set(RatingFile.class)
                .to(new File("res/data/ratings.csv"));

        // use custom item and user DAOs
        // specify item DAO implementation with tags
        config.bind(ItemDAO.class)
                .to(CSVItemTagDAO.class);
        // specify tag file
        config.set(TagFile.class)
                .to(new File(""));
        // and title file
        config.set(TitleFile.class)
                .to(new File(""));

        // our user DAO can look up by user name
        config.bind(UserDAO.class)
                .to(MOOCUserDAO.class);
        config.set(UserFile.class)
                .to(new File(""));

        // use the TF-IDF scorer you will implement to score items
        config.bind(ItemScorer.class)
                .to(TFIDFItemScorer.class);
        return config;
    }
}

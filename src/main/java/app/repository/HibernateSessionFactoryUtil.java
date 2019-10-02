package app.repository;

import app.model.*;
import app.model.base.CoreEntity;
import app.model.base.CreatedByMemberEntity;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .addAnnotatedClass(Artist.class)
                        .addAnnotatedClass(Author.class)
                        .addAnnotatedClass(Book.class)
                        .addAnnotatedClass(BookExcerpt.class)
                        .addAnnotatedClass(CoreEntity.class)
                        .addAnnotatedClass(FollowedItem.class)
                        .addAnnotatedClass(FollowedItemId.class)
                        .addAnnotatedClass(ItemType.class)
                        .addAnnotatedClass(Member.class)
                        .addAnnotatedClass(Release.class)
                        .addAnnotatedClass(Song.class)
                        .addAnnotatedClass(SongExcerpt.class)
                        .addAnnotatedClass(CreatedByMemberEntity.class)
                        .buildSessionFactory();

            } catch (HibernateException e){
                System.out.println("Session factory creation failed: " + e.getMessage());
            }
        }
        return sessionFactory;
    }
}

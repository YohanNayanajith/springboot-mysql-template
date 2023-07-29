package com.example.mysqltemplate.dao;

import com.example.mysqltemplate.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseJdbcDAO implements DAO<Course> {
    private static final Logger logger = LoggerFactory.getLogger(CourseJdbcDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Course> courseRowMapper = (rs, rowNum) -> {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setLink(rs.getString("link"));
        return course;
    };

    @Override
    public List<Course> list() {
        String query = "SELECT course_id, title, description, link FROM course";
//        return jdbcTemplate.query(query, (rs, rowNum)->{
//            Course course = new Course();
//            course.setCourseId(rs.getInt("course_id"));
//            course.setTitle(rs.getString("title"));
//            course.setDescription(rs.getString("description"));
//            course.setLink(rs.getString("link"));
//            return course;
//        });
        return jdbcTemplate.query(query, courseRowMapper);
    }

    @Override
    public void create(Course course) {
        String query = "INSERT INTO course(title, description, link) VALUES(?,?,?)";

        int result = jdbcTemplate.update(query, course.getTitle(), course.getDescription(), course.getLink());
        if (result == 1) {
            logger.info("Course is created");
        } else {
            logger.error("Course create fail");
        }
    }

    @Override
    public Optional<Course> get(int id) {
        String query = "SELECT course_id,title,description,link FROM course WHERE course_id = ?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(query, new Object[]{id}, courseRowMapper);
        } catch (DataAccessException e) {
            logger.info(id + " course is not found");
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void update(Course course, int id) {
        String query = "UPDATE course SET title=?,description=?,link=? WHERE course_id=?";
        int update = jdbcTemplate.update(query,course.getTitle(),course.getDescription(), course.getLink(), id);
        if(update == 1){
            logger.info("Course update success");
        }else{
            logger.info("Course update fail");
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM course WHERE course_id=?";
        int delete = jdbcTemplate.update(query,id);
        if(delete == 1){
            logger.info("Course delete success");
        }else {
            logger.info("Course delete unsuccessful");
        }
    }
}

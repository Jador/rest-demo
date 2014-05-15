package demo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * [Class Description Here]
 *
 * @author Tyler Graham
 */
public class Response<T> {

    private List<T> entities;
    private List<String> errors;
    private boolean success;

    public Response() {
        entities = new ArrayList<T>();
        errors = new ArrayList<String>();
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

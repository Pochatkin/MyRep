package java.Interface;

/**
 * Created by Михаил on 21.11.2015.
 */
public interface Transformer<I,O> {
        O transform(I input);
}

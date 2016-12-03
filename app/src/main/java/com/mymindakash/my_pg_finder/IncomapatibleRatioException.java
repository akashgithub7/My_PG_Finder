package com.mymindakash.my_pg_finder;

/**
 * Created by Akash117759 on 02-12-2016.
 */

public class IncomapatibleRatioException extends RuntimeException {

    private static final long serialVersionUID = 234608108593115395L;

    public IncomapatibleRatioException() {
        super("Can't perform Ken Burns effect on rects with distinct aspect ratios!");
    }
}

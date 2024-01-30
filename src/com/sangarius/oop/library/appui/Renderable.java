package com.sangarius.oop.library.appui;

import java.io.IOException;

/**
 * The interface representing an object that can be rendered, typically used for rendering user interfaces.
 */
public interface Renderable {

    /**
     * Renders the object, typically involving displaying some form of user interface.
     *
     * @throws IOException if an I/O error occurs while rendering.
     */
    void render() throws IOException;
}

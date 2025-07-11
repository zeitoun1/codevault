package com.zeitoun.codevault.app;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class responsible for switching scenes and child nodes in scenes
 * currentScene: the scene currently being shown to the user
 * nodeMap: map of all the possible nodes that can be displayed in the application with a name as a key
 *
 *
 */
public class SceneManager {

    private Scene currentScene;
    private Map<String, Node> nodeMap = new HashMap<>();


    /**
     * Adds a new Node (view) to the nodeMap
     * @param name name of the view
     * @param node the root of the view
     */
    public void addNode(String name, Node node) {
        nodeMap.put(name, node);
    }

    /**
     * Adds a new node to the children of the root in the currentScene, if it is already a child it does nothing
     * @param name name of the view
     * @param pos the index the new child node will be inserted in
     */
    public void addNodeToRoot(String name, int pos) {
        Pane currentPane = (Pane) currentScene.getRoot();
        if(currentPane.getChildren().contains(nodeMap.get(name))) {
            return ;
        } else {
            currentPane.getChildren().add(pos, nodeMap.get(name));
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

}

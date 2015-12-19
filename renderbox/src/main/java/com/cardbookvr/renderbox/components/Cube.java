package com.cardbookvr.renderbox.components;

import com.cardbookvr.renderbox.materials.VertexColorLightingMaterial;

import java.nio.FloatBuffer;

/**
 * Created by Jonathan on 12/12/2015.
 */
public class Cube extends RenderObject {
    private static FloatBuffer cubeVertices;
    private static FloatBuffer cubeColors;
    private static FloatBuffer cubeNormals;

    public Cube(){
        super();
        allocateBuffers();
        createMaterial();
    }

    public static void allocateBuffers(){
        //Already setup?
        if (cubeVertices != null)
            return;
        cubeVertices = allocateFloatBuffer(CUBE_COORDS);
        cubeColors = allocateFloatBuffer(cubeFacesToArray(CUBE_COLORS_FACES, 4));
        cubeNormals = allocateFloatBuffer(cubeFacesToArray(CUBE_NORMALS_FACES, 3));
    }

    public void createMaterial(){
        //VertexColorMaterial vcm = new VertexColorMaterial();
        //vcm.setBuffers(cubeVertices, cubeColors, 36);
        VertexColorLightingMaterial vcm = new VertexColorLightingMaterial();
        vcm.setBuffers(cubeVertices, cubeColors, cubeNormals, 36);
        material = vcm;
    }

    public static final float[] CUBE_COORDS = new float[] {
            // Front face
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,

            // Right face
            1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, -1.0f,

            // Back face
            1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,

            // Left face
            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,

            // Top face
            -1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f,

            // Bottom face
            1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
    };

    public static final float[] CUBE_COLORS_FACES = new float[] {
            // front, green
            0f, 0.5273f, 0.2656f, 1.0f,
            // right, blue
            0.0f, 0.3398f, 0.9023f, 1.0f,
            // back, also green
            0f, 0.5273f, 0.2656f, 1.0f,
            // left, also blue
            0.0f, 0.3398f, 0.9023f, 1.0f,
            // top, red
            0.8359375f,  0.17578125f,  0.125f, 1.0f,
            // bottom, also red
            0.8359375f,  0.17578125f,  0.125f, 1.0f,
    };

    public static final float[] CUBE_NORMALS_FACES = new float[] {
            // Front face
            0.0f, 0.0f, 1.0f,
            // Right face
            1.0f, 0.0f, 0.0f,
            // Back face
            0.0f, 0.0f, -1.0f,
            // Left face
            -1.0f, 0.0f, 0.0f,
            // Top face
            0.0f, 1.0f, 0.0f,
            // Bottom face
            0.0f, -1.0f, 0.0f,
    };

    /**
     * Utility method for generating float arrays for cube faces
     *
     * @param model - float[] array of values per face.
     * @param coords_per_vertex - int number of coordinates per vertex.
     * @return - Returns float array of coordinates for triangulated cube faces.
     *               6 faces X 6 points X coords_per_vertex
     */
    public static float[] cubeFacesToArray(float[] model, int coords_per_vertex) {
        float coords[] = new float[6 * 6 * coords_per_vertex];
        int index = 0;
        for (int iFace=0; iFace < 6; iFace++) {
            for (int iVertex=0; iVertex < 6; iVertex++) {
                for (int iCoord=0; iCoord < coords_per_vertex; iCoord++) {
                    coords[index] = model[iFace*coords_per_vertex + iCoord];
                    index++;
                }
            }
        }
        return coords;
    }

}

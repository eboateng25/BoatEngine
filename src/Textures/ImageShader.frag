#version 110

uniform float in_alpha;
uniform sampler2D texture1; //Samplers are data types used to access textures. //To use textures from your main program, this must be uniform.
vec4 color;
float grayscale;
float alpha;


void main() {
    color =  texture2D(texture1, gl_TexCoord[0].st);
    grayscale = 0.299*(color.r) + 0.587*(color.g) + 0.114*(color.b);
    alpha = in_alpha;
    if(color.a == 0.0){
        alpha = 0.0;
    }
    gl_FragColor = vec4(color.r,color.g,color.b,alpha);

    //gl_FragColor you already should know. texture2D is a built in function used to retrieve a particular texel from a sampler. It
    //takes a sampler2D and a vec2 argument. If your confused about the .st : remember you can access elements in a vector using s, t
    //p, q. But these can also be used to get more than one element at a time. So st gives a vec2 of the first and second elements.
    //It probably won't surprise you that similar samplers also exist for 1D and 3D textures. They are:
    //  Type: sampler1D  Function: texture1D(sampler1D, float)
    //  Type: sampler3D  Function: texture3D(sampler3D, vec3)
    // But there are a whole host of functions to achieve various texture sampling objectives such as projection and offsets. These I
    // have never used and will not go into. They are there if you want to research.
}
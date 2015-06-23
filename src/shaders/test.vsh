varying vec4 vertColor;
varying vec2 vUv;

void main()
{
	float factor = 0;
	vec4 v = vec4(gl_Vertex);
	
	gl_Position = gl_ModelViewProjectionMatrix * v;
	
	float d = gl_Position.x*gl_Position.x + gl_Position.z*gl_Position.z;
	
	d = sqrt(d);
	
	gl_Position.y += (d)*factor;
	
	vertColor = gl_Color;
}
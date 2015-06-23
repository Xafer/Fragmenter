varying vec4 vertColor;
varying vec2 vUv;

float interpolation1(float a)
{
	return 1-(cos(a* 3.1416)+1)/2;
}

void main()
{
	
	vec4 fogColor = vec4(0.132,0,0.066,1.0);
	
	gl_FragColor = vertColor;
	
	float x = (gl_FragCoord.x/gl_FragCoord.w);
	float y = (gl_FragCoord.y/gl_FragCoord.w);
	float z = (gl_FragCoord.z/gl_FragCoord.w);
	
	float f = (z/3);
	
	float factor = min(1.0,max(0.0,f/2));
	
	float value = interpolation1(factor);
	
	vec4 newColor = vec4(fogColor.rgb,1);
	newColor.rgb *= value;
	newColor.rgb += gl_FragColor.rgb*(1-value);
	
	if(z == 0)
	{
		newColor = vec4(1,1,1,1);
	}
	
	gl_FragColor = vec4(newColor.rgb,1);
}
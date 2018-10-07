int brick_width = 30;
int brick_height = 12;
int bricks_in_base = 14;
void setup(){
size(500,500);
int totalBricks = 0;
int br = bricks_in_base;
for(int i = 0; i < bricks_in_base; i++){
  totalBricks += br;
  br -= 1;
}
int counter = 0;
int pyrWidth = (brick_width*bricks_in_base);
int startPos = (width-pyrWidth)/2;
int yPos = height-12;
for(int i = 0; i < totalBricks; i++){
rect(startPos, yPos, brick_width, brick_height);
startPos += brick_width;
counter += 1;
if(counter >= bricks_in_base){
  counter = 0;
  bricks_in_base -= 1;
  yPos -= 12;
  pyrWidth -= brick_width;
  startPos = (width-pyrWidth)/2;
}
}
}
void draw(){


  
  
  
}
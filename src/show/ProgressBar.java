package show;

/*
 * Copyright 2014 Miguel Ángel Rodríguez-García (miguel.rodriguezgarcia@kaust.edu.sa).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * A progress bar is implemented on this class.
 *
 * @author Miguel Ángel Rodríguez-García.
 * @version 1.0
 */
public class ProgressBar {

    /**
     * It prints the percentage of the process performed as a progress bar.
     * @param percentage Percentage of the process performed.
     * @param message Message to show.
     */
    public void printProgressBar(int percentage,String message){
        StringBuilder bar = new StringBuilder("[");

        for(int i = 0; i < 50; i++){
            if( i < (percentage/2)){
                bar.append("=");
            }else if( i == ((int)percentage/2)){
                bar.append(">");
            }else{
                bar.append(" ");
            }
        }

        bar.append("]   " + percentage + "%\t"+message);
        System.out.print("\r" + bar.toString());
    }
}

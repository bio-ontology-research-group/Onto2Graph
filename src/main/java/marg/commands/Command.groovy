package marg.commands;

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
 * The type of class to return when querying for matches to a class description
 * in an OWL ontology.
 *
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */

/**
 * Command interface that should be implemented by all commands in the application.
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public interface Command {
    /**
     * It is responsible of the execution of the command.
     */
    public void executeCommand();
}